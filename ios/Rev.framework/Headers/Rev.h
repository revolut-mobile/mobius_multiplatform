#import <Foundation/Foundation.h>

@class RevCardsInteractor, RevCardsRepository, RevRevolutCardImpl, RevStdlibUnit, RevBasePresenter, RevCardsPresenter, RevAsyncDispatcher, RevContinuationDispatcher, RevStdlibAbstractCoroutineContextElement, RevStdlibThrowable, RevDispatchedContinuation, RevEmptyContinuation, RevEmptyContinuationCompanion;

@protocol RevRevolutCard, RevBaseView, RevCardsView, RevStdlibCoroutineContextElement, RevStdlibCoroutineContext, RevStdlibCoroutineContextKey, RevStdlibContinuationInterceptor, RevStdlibContinuation, RevStdlibSuspendFunction0, RevStdlibSuspendFunction;

NS_ASSUME_NONNULL_BEGIN

@interface KotlinBase : NSObject
-(instancetype) init __attribute__((unavailable));
+(instancetype) new __attribute__((unavailable));
+(void)initialize __attribute__((objc_requires_super));
@end;

@interface KotlinBase (KotlinBaseCopying) <NSCopying>
@end;

__attribute__((objc_runtime_name("KotlinMutableSet")))
@interface RevMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end;

__attribute__((objc_runtime_name("KotlinMutableDictionary")))
@interface RevMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end;

__attribute__((objc_subclassing_restricted))
@interface RevCardsInteractor : KotlinBase
-(instancetype)initWithCardsRepository:(RevCardsRepository*)cardsRepository NS_SWIFT_NAME(init(cardsRepository:)) NS_DESIGNATED_INITIALIZER;

@end;

@protocol RevRevolutCard
@required
-(void)printIdAsync NS_SWIFT_NAME(printIdAsync());
-(void)runAsyncL:(RevStdlibUnit*(^)(void))l NS_SWIFT_NAME(runAsync(l:));
@property (readonly) NSString* id;
@end;

__attribute__((objc_subclassing_restricted))
@interface RevRevolutCardImpl : KotlinBase <RevRevolutCard>
-(instancetype)initWithId:(NSString*)id NS_SWIFT_NAME(init(id:)) NS_DESIGNATED_INITIALIZER;

@end;

@interface RevCardsRepository : KotlinBase
-(instancetype)init NS_SWIFT_NAME(init()) NS_DESIGNATED_INITIALIZER;

-(NSArray<id<RevRevolutCard>>*)getAllCardsSync NS_SWIFT_NAME(getAllCardsSync());
@end;

@interface RevBasePresenter : KotlinBase
-(instancetype)init NS_SWIFT_NAME(init()) NS_DESIGNATED_INITIALIZER;

-(void)attachView:(id<RevBaseView>)view NS_SWIFT_NAME(attach(view:));
-(void)detach NS_SWIFT_NAME(detach());
-(void)onViewAttached NS_SWIFT_NAME(onViewAttached());
-(void)onViewDetached NS_SWIFT_NAME(onViewDetached());
@property (readonly) id<RevBaseView> _Nullable view;
@end;

@protocol RevBaseView
@required
@end;

__attribute__((objc_subclassing_restricted))
@interface RevCardsPresenter : RevBasePresenter
-(instancetype)initWithInteractor:(RevCardsInteractor*)interactor NS_SWIFT_NAME(init(interactor:)) NS_DESIGNATED_INITIALIZER;

-(instancetype)init NS_SWIFT_NAME(init()) NS_DESIGNATED_INITIALIZER __attribute__((unavailable));

-(void)start NS_SWIFT_NAME(start());
-(void)attachView:(id<RevCardsView>)view NS_SWIFT_NAME(attach(view:));
@property (readonly) id<RevCardsView> _Nullable view;
@end;

@protocol RevCardsView <RevBaseView>
@required
-(void)showCardList:(NSArray<id<RevRevolutCard>>*)list NS_SWIFT_NAME(showCard(list:));
@end;

@protocol RevStdlibCoroutineContext
@required
-(id _Nullable)foldInitial:(id _Nullable)initial operation:(id _Nullable(^)(id _Nullable, id<RevStdlibCoroutineContextElement>))operation NS_SWIFT_NAME(fold(initial:operation:));
-(id<RevStdlibCoroutineContextElement> _Nullable)getKey:(id<RevStdlibCoroutineContextKey>)key NS_SWIFT_NAME(get(key:));
-(id<RevStdlibCoroutineContext>)minusKeyKey:(id<RevStdlibCoroutineContextKey>)key NS_SWIFT_NAME(minusKey(key:));
-(id<RevStdlibCoroutineContext>)plusContext:(id<RevStdlibCoroutineContext>)context NS_SWIFT_NAME(plus(context:));
@end;

@protocol RevStdlibCoroutineContextElement <RevStdlibCoroutineContext>
@required
@property (readonly) id<RevStdlibCoroutineContextKey> key;
@end;

@interface RevStdlibAbstractCoroutineContextElement : KotlinBase <RevStdlibCoroutineContextElement>
-(instancetype)initWithKey:(id<RevStdlibCoroutineContextKey>)key NS_SWIFT_NAME(init(key:)) NS_DESIGNATED_INITIALIZER;

@end;

@protocol RevStdlibContinuationInterceptor <RevStdlibCoroutineContextElement>
@required
-(id<RevStdlibContinuation>)interceptContinuationContinuation:(id<RevStdlibContinuation>)continuation NS_SWIFT_NAME(interceptContinuation(continuation:));
@end;

@interface RevContinuationDispatcher : RevStdlibAbstractCoroutineContextElement <RevStdlibContinuationInterceptor>
-(instancetype)init NS_SWIFT_NAME(init()) NS_DESIGNATED_INITIALIZER;

-(instancetype)initWithKey:(id<RevStdlibCoroutineContextKey>)key NS_SWIFT_NAME(init(key:)) NS_DESIGNATED_INITIALIZER __attribute__((unavailable));

-(BOOL)dispatchResumeValue:(id _Nullable)value continuation:(id<RevStdlibContinuation>)continuation NS_SWIFT_NAME(dispatchResume(value:continuation:));
-(BOOL)dispatchResumeWithExceptionException:(RevStdlibThrowable*)exception continuation:(id<RevStdlibContinuation>)continuation NS_SWIFT_NAME(dispatchResumeWithException(exception:continuation:));
@end;

__attribute__((objc_subclassing_restricted))
@interface RevAsyncDispatcher : RevContinuationDispatcher
-(instancetype)init NS_SWIFT_NAME(init()) NS_DESIGNATED_INITIALIZER;

@end;

@protocol RevStdlibContinuation
@required
-(void)resumeValue:(id _Nullable)value NS_SWIFT_NAME(resume(value:));
-(void)resumeWithExceptionException:(RevStdlibThrowable*)exception NS_SWIFT_NAME(resumeWithException(exception:));
@property (readonly) id<RevStdlibCoroutineContext> context;
@end;

__attribute__((objc_subclassing_restricted))
@interface RevDispatchedContinuation : KotlinBase <RevStdlibContinuation>
-(instancetype)initWithDispatcher:(RevContinuationDispatcher*)dispatcher continuation:(id<RevStdlibContinuation>)continuation NS_SWIFT_NAME(init(dispatcher:continuation:)) NS_DESIGNATED_INITIALIZER;

@end;

@interface RevEmptyContinuation : KotlinBase <RevStdlibContinuation>
-(instancetype)initWithContext:(id<RevStdlibCoroutineContext>)context NS_SWIFT_NAME(init(context:)) NS_DESIGNATED_INITIALIZER;

@end;

__attribute__((objc_subclassing_restricted))
@interface RevEmptyContinuationCompanion : RevEmptyContinuation
+(instancetype)alloc __attribute__((unavailable));
+(instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));

+(instancetype)companion NS_SWIFT_NAME(init());

-(instancetype)initWithContext:(id<RevStdlibCoroutineContext>)context NS_SWIFT_NAME(init(context:)) NS_DESIGNATED_INITIALIZER __attribute__((unavailable));

@end;

__attribute__((objc_subclassing_restricted))
@interface RevUtils : KotlinBase
+(void)launchContext:(id<RevStdlibCoroutineContext>)context block:(id<RevStdlibSuspendFunction0>)block NS_SWIFT_NAME(launch(context:block:));
@end;

__attribute__((objc_subclassing_restricted))
@interface RevStdlibUnit : KotlinBase
+(instancetype)alloc __attribute__((unavailable));
+(instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));

+(instancetype)unit NS_SWIFT_NAME(init());

@end;

@protocol RevStdlibCoroutineContextKey
@required
@end;

@interface RevStdlibThrowable : KotlinBase
-(instancetype)initWithMessage:(NSString* _Nullable)message NS_SWIFT_NAME(init(message:)) NS_DESIGNATED_INITIALIZER;

-(instancetype)initWithCause:(RevStdlibThrowable* _Nullable)cause NS_SWIFT_NAME(init(cause:)) NS_DESIGNATED_INITIALIZER;

-(instancetype)init NS_SWIFT_NAME(init()) NS_DESIGNATED_INITIALIZER;

-(instancetype)initWithMessage:(NSString* _Nullable)message cause:(RevStdlibThrowable* _Nullable)cause NS_SWIFT_NAME(init(message:cause:)) NS_DESIGNATED_INITIALIZER;

-(void)printStackTrace NS_SWIFT_NAME(printStackTrace());
@property (readonly) RevStdlibThrowable* _Nullable cause;
@property (readonly) NSString* _Nullable message;
@end;

@protocol RevStdlibSuspendFunction
@required
@end;

@protocol RevStdlibSuspendFunction0 <RevStdlibSuspendFunction>
@required
@end;

NS_ASSUME_NONNULL_END
